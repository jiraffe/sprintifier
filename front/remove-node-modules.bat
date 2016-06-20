@echo off

rem -------------------------------------------------------------
rem  Remove npm pkg for project.
rem -------------------------------------------------------------

@setLocal EnableExtensions EnableDelayedExpansion

cd %~dp0

for /d %%a in ("node_modules\*") do (
    npm rm %%~na
)

pause

@endlocal