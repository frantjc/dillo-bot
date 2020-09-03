import {
    DefaultProps,
} from '.';

export interface ThProps extends DefaultProps {
    scope:
        | 'row'
        | 'col';
}

export default function Th(props: ThProps): JSX.Element;
