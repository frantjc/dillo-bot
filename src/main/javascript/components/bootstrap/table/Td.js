import React from 'react';

function Td({ children, onClick }) {
  return <td onClick={onClick}>{children}</td>;
}

export default Td;
