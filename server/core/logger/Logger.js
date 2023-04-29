const { createLogger, format, transports } = require('winston');
const { combine, timestamp, label, printf } = format;

const logFormatter = printf(({level, message, timestamp})=>{
    return `[${level.toUpperCase()}] : ${timestamp} ${message}`;
})

const logger = createLogger({
  level: 'debug',
  options: { flags: 'w' },
  format: combine(
    timestamp(),
    logFormatter
  ),
  transports: [
    new transports.File({ filename: 'logs/error.log', level: 'error' }),
    new transports.File({ filename: 'logs/system.log' }),
  ],
});



//
// If we're not in production then log to the `console` with the format:
// `${info.level}: ${info.message} JSON.stringify({ ...rest }) `
//
if (process.env.NODE_ENV !== 'production') {
  logger.add(new transports.Console({
    format: combine(
        timestamp(),
        logFormatter
      )
  }));
}

module.exports = logger;