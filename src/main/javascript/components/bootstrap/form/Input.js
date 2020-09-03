import React from 'react';

import Label from './Label';

import { classes } from '../../../styles';

function Input({
  label,
  type,
  placeholder,
  name,
  onChange,
  onKeyPress,
  id,
  help,
  prepend,
  disabled,
  valid,
  style,
  className,
}) {
  const inputProps = {
    className: `${classes.formControl}${type === 'file' ? '-file' : ''}${
      className ? ` ${className}` : ''
    }`,
    type,
    onChange,
    placeholder,
    name,
    disabled,
    onKeyPress,
    'aria-describedby': help ? `${id}-help` : null,
    style,
  };

  return (
    <>
      {label ? <Label inputId={id}>{label}</Label> : null}
      {prepend ? (
        <div className={classes.inputGroup}>
          <div className={classes.inputGroupPrepend}>
            <div className={classes.inputGroupText}>{prepend}</div>
          </div>
          <input {...inputProps} />
        </div>
      ) : (
        <input {...inputProps} />
      )}
      {help ? (
        <small id={`${id}-help`} className={classes.formHelp}>
          {help}
        </small>
      ) : null}
    </>
  );
}

export default Input;
