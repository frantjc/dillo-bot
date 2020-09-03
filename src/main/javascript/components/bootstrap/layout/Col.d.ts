import {
    DefaultProps,
} from '.';

export interface ColProps extends DefaultProps {
    width:
        | 1
        | 2
        | 3
        | 4
        | 5
        | 6
        | 7
        | 8
        | 9
        | 10
        | 11
        | 12
        | 'auto'
        | 'xs'
        | 'sm'
        | 'md'
        | 'lg'
        | 'xl';
}

export default function Col(props: ColProps): JSX.Element;