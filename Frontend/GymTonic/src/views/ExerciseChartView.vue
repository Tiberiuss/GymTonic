<script setup lang="ts">
import * as d3 from 'd3';
import {computed, onBeforeMount, ref} from "vue";
import {workoutService} from "@/services/workout.service";
import {useRoute} from "vue-router";

const route = useRoute();
const svg = ref();
const data = ref<Array<Array<[string, number]>>>();
const exerciseName = ref();

const marks = computed(() => data.value!==undefined ? ({
  start:data.value[0][1],
  peak:Math.max(...data.value.map(d=>+(d[1]))),
  end:data.value[data.value.length-1][1],
  change:+(data.value[data.value.length-1][1]) - +(data.value[0][1])
}) : ({
  start:0,
  peak:0,
  end:0,
  change:0
}))

const margin = {top: 10, right: 50, bottom: 50, left: 50};
const w = 500;
const h = 500;
const width = w - margin.left - margin.right;
const height = h - margin.top - margin.bottom;
//const data = [["5/07/2023",1],["7/07/2023",2],["10/07/2023",5],["12/07/2023",1]]



onBeforeMount(async () => {
  const {result:logs} = await workoutService.getAll(route.params.itemId);
  console.log(logs);
  let charts = logs?.data.charts;
  charts.forEach(d => {
    console.log(d.date)
    d.date = d3.timeParse("%Y-%m-%d")(d.date)
  })
  data.value = charts.length>0 ? charts.map(l => [l.date,l.weight]) : undefined;

  const dataPoints = data.value ?? [];
  
  exerciseName.value = logs?.data.exerciseName;

  // CHART
  const g = d3.select(svg.value)
      .append("g")
      .attr("transform", `translate(${margin.left},${margin.top})`);
  // X axis
  const x = d3.scaleTime().domain(d3.extent(dataPoints,d => d[0])).range([0,width]);
  g.append('g')
      .attr("transform", "translate(0," + height + ")")
      .attr('class', 'axis x')
      .call(d3.axisBottom(x).ticks(5).tickSize(-height).tickPadding(10));
  // Y axis
  const y = d3.scaleLinear().domain([0,marks.value.peak+5]).range([height,0]);
  g.append('g')
      .attr('class', 'axis y')
      .call(d3.axisLeft(y).tickSize(-width).tickPadding(10));

  // GRID lines
  g.selectAll(".tick line")
      .attr("opacity",0.5);

  // AXIS legend
  g.append("text")
      .attr("fill","white")
      .attr("text-anchor", "end")
      .attr("x", width)
      .attr("y", height + margin.top + 20)
      .text("Date");
  g.append("text")
      .attr("fill","white")
      .attr("text-anchor", "end")
      .attr("transform", "rotate(-90)")
      .attr("y", -margin.left+20)
      .attr("x", -margin.top)
      .text("Weight")

  // PATH
  const path = g.append("g");

  path.append("g")
      .datum(dataPoints)
      .attr("fill", "none")
      .attr("stroke", "steelblue")
      .attr("stroke-width", 1.5)
      .append("path")
        .attr("d", d3.line()
            .x(d => {
              console.log(d); return x(d[0])})
            .y(d => y(d[1]))
        );

  path.append("g")
      .selectAll("circles")
      .data(dataPoints)
      .enter()
      .append("circle")
        .attr("cx",(d) => x(d[0]))
        .attr("cy",(d) => y(d[1]))
        .attr("r",6)
        .style("fill","blue")

  path.style("opacity", 0)
      .transition()
      .duration(1000)
      .style("opacity", 1);
})

</script>

<template>
  <div class="chart">
      <h1>{{exerciseName}}</h1>
      <div class="chart__results">
        <svg class="chart__svg" ref="svg" :viewBox="`0 0 ${w} ${h}`"></svg>
        <table class="table">
            <thead>
            <tr>
              <th>START</th>
              <th>PEAK</th>
              <th>END</th>
              <th>CHANGE</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td>{{ marks.start }}kg</td>
              <td>{{ marks.peak }}kg</td>
              <td>{{ marks.end }}kg</td>
              <td :style="{color: marks.change>=0 ? 'yellowgreen' : 'red'}">{{ marks.change }}kg</td>
            </tr>
            </tbody>
        </table>
      </div>
  </div>
</template>

<style lang="scss">
  .chart {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
      margin-top: 2rem;
      padding: 0 1rem;

    &__svg {
      background-color: #2c3e50;
      padding: 1rem;
      border-radius: 1rem;
      width: 100%;
    }

    &__results{
      width: 45em;
      padding: 0 5em;

      @media (max-width: 580px) {
        width: 30em;
      }
    }
  
  }

  .table {
    color: white;
  }


</style>