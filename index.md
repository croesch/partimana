---
layout: default
title: Manage your camps
---

# Partimana

Partimana is a simple Java Swing application that helps you managing camps and their participants. It's hosted [on github](https://github.com/croesch/partimana).

## Getting started

1. [Download](downloads.html) partimana
1. Extract the contents of the zip-file to a directory of your choice
1. Create a database in your MySQL database using `partimana.sql`
1. Add `db.properties` to `config` folder, that looks like this:
^
        # url to the jdbc database. Use correct host, port and database for being lucky!
        db.url      = jdbc:mysql://localhost:1234/database?useUnicode=true&characterEncoding=utf8
        # enter the user for the database here
        db.user     = databaseUser
        # enter your password here
        db.password = secretPassword
1. Start your database
1. Run the `partimana.bat` on Windows or `partimana.sh` on Unix

Don't hesitate to contact me!
