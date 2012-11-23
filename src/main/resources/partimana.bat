@echo off
REM ###################
REM # Copyright (C) 2011-2012  Christian Roesch
REM #
REM # This file is part of partimana.
REM #
REM # partimana is free software: you can redistribute it and/or modify
REM # it under the terms of the GNU General Public License as published by
REM # the Free Software Foundation, either version 3 of the License, or
REM # (at your option) any later version.
REM #
REM # partimana is distributed in the hope that it will be useful,
REM # but WITHOUT ANY WARRANTY; without even the implied warranty of
REM # MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
REM # GNU General Public License for more details.
REM #
REM # You should have received a copy of the GNU General Public License
REM # along with partimana.  If not, see <http://www.gnu.org/licenses/>.
REM ###################

REM # directory of this script file
set DIR=%~dp0

java -cp .;"%DIR%\config";"%DIR%\partimana\*" ^
     com.github.croesch.partimana.PartiMana %*
