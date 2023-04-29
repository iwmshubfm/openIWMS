var express = require('express');
var router = express.Router();
var logger = require('../server/core/logger/Logger');
/* This code is defining a route for the root URL path "/" using the HTTP GET method. When a user
navigates to this URL, the server will render the "index" view using the Express template engine and
pass in an object with the title property set to "Express". */
router.get('/', function (req, res, next) {
  res.render('index', { title: 'Express' });
});

/* This code is defining a route for the URL path "/login" using the HTTP GET method. When a user
navigates to this URL, the server will render the "login" view using the Express template engine and
pass in an object with the title property set to "Express". */
router.get('/login', function (req, res, next) {
  res.render('login', { title: 'Express' });
});

router.get('/views/:viewName', function (req, res, next) {
  const { module, process, task } = req.query;
  if (module && process && task) {
    let url = `${module}/${process}/${task}`;
    logger.info('Accessing view name: ' + url);
    res.render(url);
  } else {
    logger.error('Required parameters not found: ' + JSON.stringify(req.query));
    res.render('404');
  }
});
module.exports = router;
