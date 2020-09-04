import React from 'react';

import { classes } from '../../../styles';

function Table({
  className,
  children,
  dark,
  striped,
  bordered,
  hover,
  sm,
  caption,
}) {
  return (
    <table
      className={`${className ? `${className} ` : ''}${classes.table}${
        dark ? ` ${classes.tableDark}` : ''
      }${striped ? ` ${classes.tableStriped}` : ''}${
        bordered ? ` ${classes.tableBordered}` : ''
      }${hover ? ` ${classes.tableHover}` : ''}${
        sm ? ` ${classes.tableSm}` : ''
      }`}>
      {caption ? <caption>{caption}</caption> : null}
      {children}
    </table>
  );
}

export default Table;
