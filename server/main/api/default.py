# -*- coding: utf-8 -*-
from flask import Blueprint, jsonify
from flask import request
import flask
import datetime
from datetime import timedelta
import requests
import json

route = Blueprint('default', __name__)

time_flights_list_updated_ts = 0
actual_flights_list = []
DAYS_FLIGHTS_API = 2


@route.route("/api/next_flight", methods=['GET'])
def get_next_flight():
    actual_time_ts = datetime.datetime.now().timestamp()
    flights = get_actual_flights_list()
    next_flights = list(filter(lambda flight: flight['time_ts'] > actual_time_ts, flights))
    sorted_by_time = sorted(next_flights, key = lambda i: i['time_ts'])
    if len(sorted_by_time) == 0:
        return jsonify({"msg":f"There are no flights in the next {DAYS_FLIGHTS_API} days"})
    next_flight = sorted_by_time[0]

    response = flask.jsonify(next_flight)
    response.headers.add('Access-Control-Allow-Origin', '*')
    return response


@route.route("/api/next_flights", methods=['GET'])
def next_several_flights():
    amount = request.args.get('amount')
    if amount is None:
        amount = 100
    actual_time_ts = datetime.datetime.now().timestamp()
    flights = get_actual_flights_list()
    next_flights = list(filter(lambda flight: flight['time_ts'] > actual_time_ts, flights))
    sorted_by_time = sorted(next_flights, key = lambda i: i['time_ts'])

    response = flask.jsonify(sorted_by_time[:amount])
    response.headers.add('Access-Control-Allow-Origin', '*')
    return response


@route.route("/api/previous_flights", methods=['GET'])
def next_previous_flights():
    amount = request.args.get('amount')
    if amount is None:
        amount = 100
    actual_time_ts = datetime.datetime.now().timestamp()
    flights = get_actual_flights_list()
    next_flights = list(filter(lambda flight: flight['time_ts'] < actual_time_ts, flights))
    sorted_by_time = sorted(next_flights, key = lambda i: i['time_ts'])

    response = flask.jsonify(sorted_by_time[:amount])
    response.headers.add('Access-Control-Allow-Origin', '*')
    return response


def get_actual_flights_list():
    global time_flights_list_updated_ts
    global actual_flights_list

    actual_time_ts = datetime.datetime.now().timestamp()
    delta_ts = 15 * 60 * 1000 # 15 minutes

    # to not re trigger foreign service so often
    if time_flights_list_updated_ts < (actual_time_ts - delta_ts) or len(actual_flights_list) == 0:
        actual_flights_list = []
        date_now = datetime.datetime.today()
        id = 0

        if DAYS_FLIGHTS_API == 0:
            raise Exception(f"{DAYS_FLIGHTS_API} has not to be zero!")

        for i in range(DAYS_FLIGHTS_API * -1, DAYS_FLIGHTS_API):
            date_api = date_now + timedelta(days=i)
            response = requests.get('https://api.iev.aero/api/flights/' + date_api.strftime("%d-%m-%Y"))
            flights = json.loads(response.text)
            try:
                for flight_departure in flights['body']['departure']:
                    dt_obj = datetime.datetime.strptime(flight_departure['timeDepShedule'], '%Y-%m-%dT%H:%M:%SZ')
                    actual_flights_list.append({"id": id,
                                                "departure": True,
                                                "time_ts": datetime.datetime.timestamp(dt_obj),
                                                "flight_number": flight_departure.get('fltNo', None),
                                                "flight_type": flight_departure.get('fltTypeID.name', None),
                                                "plane_name": flight_departure.get('planeTypeID.name', None),
                                                "airport_to": flight_departure.get('airportToID.name', None),
                                                "airport_to_city": flight_departure.get('airportToID.city', None),
                                                "gate_number": flight_departure.get('gateNo', None),
                                                "time_departure": flight_departure.get('timeDepShedule', None),
                                                "time_board": flight_departure.get('timeBoard', None),
                                                "time_take_of_fact": flight_departure.get('timeTakeofFact', None),
                                                "delay_reason": flight_departure.get('delayReasonID.name', None)
                                                })
                    id = id + 1

                for flight_arrival in flights['body']['arrival']:
                    dt_obj = datetime.datetime.strptime(flight_arrival['timeArrShedule'], '%Y-%m-%dT%H:%M:%SZ')
                    actual_flights_list.append({"id": id,
                                                "departure": False,
                                                "time_ts": datetime.datetime.timestamp(dt_obj),
                                                "flight_number": flight_arrival.get('fltNo', None),
                                                "flight_type": flight_arrival.get('fltTypeID.name', None),
                                                "plane_name": flight_arrival.get('planeTypeID.name', None),
                                                "airport_from": flight_arrival.get('airportFromID.name', None),
                                                "airport_from_city": flight_arrival.get('airportFromID.city', None),
                                                "time_arrival": flight_arrival.get('timeArrShedule', None),
                                                "time_to_stand": flight_arrival.get('timeToStand', None),
                                                "time_board": flight_arrival.get('timeBoard', None),
                                                "time_take_of_fact": flight_arrival.get('timeTakeofFact', None)
                                                })
            except KeyError:
                print("Wrong JSON response was returned from foreught API")
            except:
                print("Something else went wrong")
        time_flights_list_updated_ts = actual_time_ts
    else:
        print("Returning flight list from the local cache")

    return actual_flights_list
