<template v-if="next_flight.departure">
    <div class="main">
        <div class="table-left">
            <div class="table-left__top">
                Departure
            </div>
            <div class="table-left__center">
                to
            </div>
            <div class="table-left__bottom">
                {{ next_flight.airport_to }}
            </div>
        </div>
        <div class="table-center">
            <div class="table-center__top">
                Today
            </div>
            <div class="table-center__center">
                {{ next_flight.time_departure }}
            </div>
        </div>
        <div class="table-right">
            <div class="table-right__top">
                Flight #
            </div>
            <div class="table-right__center">
                {{ next_flight.flight_number }}
            </div>
            <div class="table-right__bottom">
                Big plane
            </div>
        </div>
    </div>
</template>

<template v-else>
    <div class="main">
        <div class="table-left" >
            <div class="table-left__top">
                Arrival
            </div>
            <div class="table-left__center">
                from
            </div>
            <div class="table-left__bottom">
                {{ next_flight.airport_to }}
            </div>
        </div>
        <div class="table-center">
            <div class="table-center__top">
                Today
            </div>
            <div class="table-center__center">
                {{ next_flight.time_arrival }}
            </div>
        </div>
        <div class="table-right">
            <div class="table-right__top">
                Flight #
            </div>
            <div class="table-right__center">
                {{ next_flight.flight_number }}
            </div>
            <div class="table-right__bottom">
                Big plane
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'MainComponent',
        data () {
            return {
                next_flight_api: '/next_flight',
                next_flight: {}
            }
        },
        mounted (){
            this.$api
                .get(this.next_flight_api)
                .then(response => {
                    this.next_flight = response.data
                });
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>



</style>
