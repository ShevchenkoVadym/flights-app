# -*- coding: utf-8 -*-
"""Airplane Route for Demo application."""

from flask import Blueprint

from server.main.services.airplane_service import AirplaneService

route = Blueprint('airplane', __name__)

airplane_service = AirplaneService()

@route.route("/api/airplanes")
def test_db():
    airplanes = airplane_service.all()
    return airplanes