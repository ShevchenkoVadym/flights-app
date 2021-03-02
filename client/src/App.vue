<template>
  <div id="app">
    <div class="container">
        <div class="left">
             <PreviousFlights/>
        </div>
        <div class="box">
             <MainComponent/>
        </div>
        <div class="right">
             <NextFlights/>
        </div>
    </div>
  </div>
</template>

<script>
import MainComponent from './components/MainComponent.vue'
import NextFlights from './components/NextFlights.vue'
import PreviousFlights from './components/PreviousFlights.vue'

export default {
  name: 'App',
  components: {
      MainComponent,
      NextFlights,
      PreviousFlights
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.container {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  grid-template-rows: 1fr;
  grid-column-gap: 0px;
  grid-row-gap: 0px;
  border-top: 1px solid grey;
  border-bottom: 1px solid grey;
  height: calc(100vh - 20px);
}
.left {
  grid-area: 1 / 1 / 2 / 2;
  padding: 5px;
  border-right: 1px solid grey;
}
.right {
  grid-area: 1 / 3 / 2 / 4;
  padding: 5px;
  border-left: 1px solid grey;
}
.box {
  align-self: center;
  padding: 15px;
  grid-area: 1 / 2 / 2 / 3;
}
.main {
  display: grid;
  grid-template-columns: 1fr 2fr 1fr;
  grid-template-rows: 1fr;
  grid-column-gap: 0px;
  grid-row-gap: 0px;
}

.main .table-left {
  grid-area: 1 / 1 / 2 / 2;
  background-color: rgb(140, 140, 224);
}
.main .table-center {
  grid-area: 1 / 2 / 2 / 3;
  background-color: rgb(139, 109, 139);
}
.main .table-right {
  grid-area: 1 / 3 / 2 / 4;
  background-color: rgb(161, 161, 112);
}

.table-left {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 1fr 0.5fr 1fr;
  grid-column-gap: 0px;
  grid-row-gap: 0px;
}
.table-left .table-left__top {
  grid-area: 1 / 1 / 2 / 2;
}
.table-left .table-left__center {
  grid-area: 2 / 1 / 3 / 2;
  border-top: 3px solid green;
  border-bottom: 3px solid green;
}
.table-left .table-left__bottom {
  grid-area: 3 / 1 / 4 / 2;
}

.table-center {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 0.5fr 1fr;
  grid-column-gap: 0px;
  grid-row-gap: 0px;
  border-left: 3px solid green;
  border-right: 3px solid green;
}
.table-center .table-center__top {
  grid-area: 1 / 1 / 2 / 2;
  border-bottom: 3px solid green;
}
.table-center .table-center__center {
  grid-area: 2 / 1 / 3 / 2;
}

.table-right {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 0.2fr repeat(2, 1fr);
  grid-column-gap: 0px;
  grid-row-gap: 0px;
}
.table-right .table-right__top {
  grid-area: 1 / 1 / 2 / 2;
}
.table-right .table-right__center {
  grid-area: 2 / 1 / 3 / 2;
  border-top: 3px solid green;
  border-bottom: 3px solid green;
}
.table-right .table-right__bottom {
  grid-area: 3 / 1 / 4 / 2;
}

.left-list {
  padding: 0;
  margin: 0;
  list-style: none;
  overflow: auto;
  max-height: calc(100vh - 20px);
}
.left-list__item {
  margin-bottom: 20px;
}
</style>
