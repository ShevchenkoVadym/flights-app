<template>
    <div>
        <ul v-for="flight in next_flights" v-bind:key="flight" class="left-list">
            <li v-if="flight.departure" class="left-list__item">
                <div class="main">
                    <div class="table-left">
                        <div class="table-left__top">
                            Departure
                        </div>
                        <div class="table-left__center">
                            to
                        </div>
                        <div class="table-left__bottom">
                            {{ flight.airport_to }}
                        </div>
                    </div>
                    <div class="table-center">
                        <div class="table-center__top">
                            Today
                        </div>
                        <div class="table-center__center">
                            {{ flight.time_departure }}
                        </div>
                    </div>
                    <div class="table-right">
                        <div class="table-right__top">
                            Flight #
                        </div>
                        <div class="table-right__center">
                            {{ flight.flight_number }}
                        </div>
                        <div class="table-right__bottom">
                            Small plane
                        </div>
                    </div>
                </div>
            </li>

            <li class="left-list__item" v-else>
                <div class="main">
                    <div class="table-left">
                        <div class="table-left__top">
                            Arrival
                        </div>
                        <div class="table-left__center">
                            from
                        </div>
                        <div class="table-left__bottom">
                            {{ flight.airport_from }}
                        </div>
                    </div>
                    <div class="table-center">
                        <div class="table-center__top">
                            Today
                        </div>
                        <div class="table-center__center">
                            {{ flight.time_arrival }}
                        </div>
                    </div>
                    <div class="table-right">
                        <div class="table-right__top">
                            Flight #
                        </div>
                        <div class="table-right__center">
                            {{ flight.flight_number }}
                        </div>
                        <div class="table-right__bottom">
                            Big plane
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
    export default {
        name: 'NextFlights',
        data () {
            return {
                next_flights_api: '/next_flights',
                next_flights: []
            }
        },
        mounted (){
            this.$api
                .get(this.next_flights_api)
                .then(response => {
                    this.next_flights = response.data
                });
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>



</style>
