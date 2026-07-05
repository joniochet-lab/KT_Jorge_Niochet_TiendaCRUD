@echo off
powershell -Command "Invoke-RestMethod -Uri 'http://localhost:8080/api/productos' | ConvertTo-Json -Depth 5"
pause
