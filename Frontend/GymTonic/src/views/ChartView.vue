<script setup lang="ts">
import * as d3 from 'd3';
import {onMounted, ref} from "vue";
import {types} from "sass";
import Number = types.Number;
import { useRouter } from 'vue-router';

const router = useRouter()

const svg = ref();
const margin = {top: 10, right: 50, bottom: 50, left: 50};
const w = 500;
const h = 500;
const width = w - margin.left - margin.right;
const height = h - margin.top - margin.bottom;
const data = [["5/07/2023",1],["7/07/2023",2],["10/07/2023",5],["12/07/2023",1]]
const marks = {
  start:data[0][1],
  peak:Math.max(...data.map(d=>+(d[1]))),
  end:data[data.length-1][1],
  change:+(data[data.length-1][1]) - +(data[0][1])
}


data.forEach(d => {
  d[0] = d3.timeParse("%d/%m/%Y")(d[0])
})

onMounted(() => {
  // CHART
  const g = d3.select(svg.value)
      .attr("width", width + margin.left + margin.right)
      .attr("height", height + margin.top + margin.bottom)
      .append("g")
      .attr("transform", `translate(${margin.left},${margin.top})`);
  // X axis
  const x = d3.scaleTime().domain(d3.extent(data,d => d[0])).range([0,width]);
  g.append('g')
      .attr("transform", "translate(0," + height + ")")
      .attr('class', 'axis x')
      .call(d3.axisBottom(x).ticks(5).tickSize(-height).tickPadding(10));
  // Y axis
  const y = d3.scaleLinear().domain([0,10]).range([height,0]);
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
      .datum(data)
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
      .data(data)
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
  <button class="back" @click="router.go(-1)">GO BACK</button>
  <div class="chart">
    <svg class="chart__svg" ref="svg" :viewBox="`0 0 ${w} ${h}`"></svg>
    <div>
      <table class="table">
        <thead>
        <tr>
          <th></th>
          <th>START</th>
          <th>PEAK</th>
          <th>END</th>
          <th>CHANGE</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>EXERCISE_NAME</td>
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
  .back {
    margin: 10px;
    border: 0px;
    border-radius: 5px;
    background-color: var(--red-color);
  }

  .chart {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;

    &__svg {
      background-color: #2c3e50;
      margin-top: 5rem;
      padding: 1rem;
      border-radius: 1rem;
    }
  }

  .table {
    color: white;
  }
</style>