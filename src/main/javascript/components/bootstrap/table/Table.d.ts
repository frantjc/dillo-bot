import {
    DefaultProps,
} from '.';

export interface TableProps extends DefaultProps {
    dark: boolean | 'true' | 'false';
    striped: boolean | 'true' | 'false';
    bordered: boolean | 'true' | 'false';
    hover: boolean | 'true' | 'false';
    sm: boolean | 'true' | 'false';
    caption: React.ReactNode;
}

export default function Table(props: TableProps): JSX.Element;
