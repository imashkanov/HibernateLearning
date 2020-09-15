@echo off
call localvars.cmd
%mysql% -u root -proot < createHibernateTestDB.sql
