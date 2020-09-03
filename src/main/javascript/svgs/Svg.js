import React from 'react';

import svgs from './index';

function Svg({ name, ...props }) {
  return (
    <img
      {...props}
      src={name && svgs[name] ? svgs[name] : props.src}
      alt={name}
    />
  );
}

export default Svg;
