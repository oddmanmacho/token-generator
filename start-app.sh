#!/bin/bash

# Function to clean up and exit
cleanup() {
    echo "Stopping Spring Boot app (PID: $springBootAppPid)..."
    kill -9 $springBootAppPid

    echo "Stopping React app (PID: $reactAppPid)..."
    kill -9 $reactAppPid

    echo "Applications stopped."
    exit 1
}

# Trap the interrupt signal (Ctrl + C) and call the cleanup function
trap cleanup INT

# Start Spring Boot app
echo "Starting back-end app..."
cd hometask
mvn spring-boot:run &
springBootAppPid=$!

# Wait for React app to start
sleep 15

# Start React app in the background
echo "Starting token generator(front-end) app..."
cd ../frontend
npm start &
reactAppPid=$!

# Wait for user input to stop the apps
echo "Press Enter to stop the applications..."
read

# Call the cleanup function when Enter is pressed
cleanup

