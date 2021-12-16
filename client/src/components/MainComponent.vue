<template>
<div class="row">
    <div class="col-md-3">
      <div style="background:green;">{{ showtime.airline.en.name }}</div>
      <div style="background:blue;">Column 2</div>
      <div style="background:yellow;">Column 3</div>
    </div>
    <div class="col-md-6">
      <div style="background:green;">{{ showtime.codeShareData[0].codeShare }}</div>
      <div style="background:white;">{{ showtime.flightTime }}</div>
    </div>
    <div class="col-md-3">
      <div style="background:green;">Column 6</div>
      <div style="background:blue;">Column 7</div>
      <div style="background:yellow;">Column 8</div>
    </div>
  </div>
</template>

<script>
    export default {
        name: 'MainComponent',
        data () {
            return {
                showtime_api: '/showtime',
                showtime: {}
            }
        },
        mounted (){
            this.$api
                .get(this.showtime_api)
                .then(response => {
                    this.showtime = response.data
                });
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>



</style>
