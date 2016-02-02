# sails-budget

A simple budgeting application built in sails js.

Add your sources of income, your state, and how much you (and your employer)
contribute to your 401K and instantly see your take home.

Add the amount you spend on categories to see how much you're left with after your
monthly bills.

Uses sails-mysql to store info.  Configure your mysql db in config/local.js.
Tax brackets and deductions are installed on first run of sails lift,
simply select which ones you are using in the app.

A variety of categories are provided to get you started.  These include:
Rent, Food, Utilities, Electricity, Internet, Phone, Savings, Car
Feel free to add your own.

UI is based on the Verti template http://html5up.net/verti
Could definitely look a lot better, but this was a quick project.  If you've got
the time, spruce it up and let me know how it turns out!

Might be some weirdness when calculating tax witholdings as < $0.01 rounding errors
over enough pay periods can cause $5-10 errors.

a [Sails](http://sailsjs.org) application
