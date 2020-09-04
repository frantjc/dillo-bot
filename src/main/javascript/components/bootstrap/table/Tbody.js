import React from 'react';

function Tbody({ className, children }) {
  return <tbody className={className ? className : null}>{children}</tbody>;
}

export default Tbody;
