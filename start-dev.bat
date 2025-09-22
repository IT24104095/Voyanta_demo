@echo off
echo Starting Voyanta Development Environment...
echo.

echo Starting Backend Server...
start "Voyanta Backend" cmd /k "mvn spring-boot:run"

echo Waiting for backend to start...
timeout /t 10 /nobreak > nul

echo Starting Frontend Server...
start "Voyanta Frontend" cmd /k "cd frontend && npm run dev"

echo.
echo Both servers are starting up...
echo Backend: http://localhost:8080
echo Frontend: http://localhost:3000
echo.
echo Press any key to exit...
pause > nul
