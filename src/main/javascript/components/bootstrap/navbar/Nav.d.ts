import {
    DefaultProps,
} from '.';

export interface NavProps extends DefaultProps {
    direction: 'left' | 'right';
}

export default function Nav(props: NavProps): JSX.Element;