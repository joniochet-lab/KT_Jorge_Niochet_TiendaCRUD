@echo off
cd /d "%~dp0..\..\backend\tienda-service"
mvn clean test
pause
