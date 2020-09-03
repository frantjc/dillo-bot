import React from 'react';

export interface SvgProps extends React.DetailedHTMLProps<React.ImgHTMLAttributes<HTMLImageElement>, HTMLImageElement> {
    name:
        | 'react';
}

export default function Svg(props: SvgProps): JSX.Element;
