import React from 'react';

function Option({ selected, value, children, style, className }) {
  return (
    <option
      selected={selected}
      value={value}
      style={style}
      className={className}>
      {children}
    </option>
  );
}

export default Option;
