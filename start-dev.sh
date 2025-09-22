#!/bin/bash

echo "Starting Voyanta Development Environment..."
echo

echo "Starting Backend Server..."
gnome-terminal -- bash -c "mvn spring-boot:run; exec bash" &

echo "Waiting for backend to start..."
sleep 10

echo "Starting Frontend Server..."
gnome-terminal -- bash -c "cd frontend && npm run dev; exec bash" &

echo
echo "Both servers are starting up..."
echo "Backend: http://localhost:8080"
echo "Frontend: http://localhost:3000"
echo
echo "Press Ctrl+C to stop all servers"
wait
