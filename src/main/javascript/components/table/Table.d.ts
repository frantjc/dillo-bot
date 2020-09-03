export type TableType = {
    name: string,
    getData: () => Promise<Object[]>,
    data: Object[],
    onTableClick: (row: Object, column: string) => void,
}

export interface TableProps {
    draggable: boolean | 'true' | 'false';
    onDragStart: (item: DragObject) => void;
    onDragEnd: (item: DragObject, monitor: DragSourceMonitor) => void;
    table: TableType;
}

export function Table(props: TableProps): JSX.Element;