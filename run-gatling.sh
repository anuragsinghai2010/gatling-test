#!/bin/bash

rm -rf /app/build/reports/gatling/*
./gradlew gatlingRun --simulation simulations.SecondSimulation