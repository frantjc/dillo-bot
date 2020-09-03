import React from 'react';

import { classes } from '../../../styles';

function TextArea({ id, rows, style, className }) {
  return (
    <textarea
      className={`${classes.formControl}${className ? ` ${className}` : ''}`}
      id={id}
      rows={rows}
      style={style}
    />
  );
}

export { TextArea };
