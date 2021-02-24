<template>
    <table>
        <thead>
        <th>To/From</th>
        <th>Destination</th>
        <th>Time</th>
        </thead>
        <tbody>
        <tr v-for="flight in next_flights" v-bind:key="flight">
            <template v-if="flight.departure">
                <td>To</td>
                <td>{{ flight.airport_to }}</td>
                <td>{{ flight.time_departure }}</td>
            </template>
            <template v-else>
                <td>From</td>
                <td>{{ flight.airport_from }}</td>
                <td>{{ flight.time_arrival }}</td>
            </template>
        </tr>
        </tbody>
    </table>
</template>

<script>
    export default {
        name: 'NextFlights',
        props: {
            msg: String
        },
        data () {
            return {
                url_local_api: {
                    next_flight_api: '/next_flights',
                    flights: '/flights'
                },

                next_flights: []
            }
        },
        created (){
            console.log("created")
        },
        mounted (){
            console.log("inside_mounter");
            this.$api
                .get(this.url_local_api.next_flight_api)
                .then(response => {
                    console.log(response.data);
                    this.next_flights = response.data
                });
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    h3 {
        margin: 40px 0 0;
    }
    ul {
        list-style-type: none;
        padding: 0;
    }
    li {
        display: inline-block;
        margin: 0 10px;
    }
    a {
        color: #42b983;
    }
</style>
