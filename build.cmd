@echo off

set workDir=%CD%
set targetDir=%CD%\target

cd %workDir%\semaphore
FOR /D /r %%G in ("v*") DO (
	cd %%G
	FOR /r %%I in (*.class) DO (
		echo %%~nI%%~xI
		call jar cvf %%~nI.jar com/epam/mentoring/classloading/model/semaphore/%%~nI%%~xI
	)
)

cd %workDir%
