import React from 'react';

function Th({ children, scope }) {
  return <th scope={scope}>{children}</th>;
}

export default Th;
