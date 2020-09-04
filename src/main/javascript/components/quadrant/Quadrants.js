import React from 'react';

import Quadrant from './Quadrant';

import { classes } from '../../styles';

function Quadrants({ children }) {
  const count = React.Children.count(children);

  let childrenArray = React.Children.toArray(children);

  if (count > 4) {
    throw new Error('cannot have more than 4 Quadrants');
  }

  while (childrenArray.length < 4) {
    childrenArray.push(<Quadrant />);
  }

  childrenArray = React.Children.toArray(
    React.Children.map(childrenArray, (child, index) =>
      child.type === Quadrant ? (
        React.cloneElement(child, {
          ...child.props,
          quadrant: index + 1,
        })
      ) : (
        <Quadrant quadrant={index + 1} />
      ),
    ),
  );

  childrenArray.unshift(...childrenArray.splice(1, 1));

  return <div className={classes.quadrants}>{childrenArray}</div>;
}

export default Quadrants;
