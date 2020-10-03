import React from 'react';

import { classes } from '../../styles';

function Quadrant({ quadrant, children }) {
  let convertedQuadrant;

  switch (quadrant) {
    case 1:
      convertedQuadrant = 'I';
      break;
    case 2:
      convertedQuadrant = 'II';
      break;
    case 3:
      convertedQuadrant = 'III';
      break;
    case 4:
      convertedQuadrant = 'IV';
      break;
    default:
      convertedQuadrant = quadrant;
  }

  return (
    <div
      className={`${classes.quadrant}${
        convertedQuadrant ? ` ${convertedQuadrant}` : ''
      }`}
    >
      {children}
    </div>
  );
}

export default Quadrant;
