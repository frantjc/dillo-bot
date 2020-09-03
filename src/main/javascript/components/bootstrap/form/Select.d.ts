import {
    DefaultProps,
} from '.';

export interface SelectProps extends DefaultProps {
    multiple: boolean;
    id: string;
    onChange: React.ChangeEventHandler<T>;
    name: string;

}

export default function Select(props: SelectProps): JSX.Element;
