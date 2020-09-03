import {
    DefaultProps,
} from '.';

export interface TrProps extends DefaultProps {
    color:
        | 'active'
        | 'primary'
        | 'secondary'
        | 'success'
        | 'danger'
        | 'warning'
        | 'info'
        | 'light'
        | 'dark';
}

export default function Tr(props: TrProps): JSX.Element;