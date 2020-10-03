import React, { useState, useEffect, useMemo } from 'react';
import { useDrag } from 'react-dnd';
import {
  FaTable as TableIcon,
  FaFrownOpen as FrownOpenIcon,
} from 'react-icons/fa';

import { DragTypes } from '../../constants';

import {
  Table as BootstrapTable,
  Tbody,
  Td,
  Th,
  Thead,
  Tr,
} from '../bootstrap/table';

import { classes, styles } from '../../styles';

function Table({
  draggable = true,
  onDragStart = (monitor) => {},
  onDragEnd = (item, monitor) => {},
  table = {},
}) {
  const [{ isDragging }, drag] = useDrag({
    item: {
      type: DragTypes.TABLE,
      ...table,
    },
    begin: (monitor) => onDragStart(monitor),
    end: (item, monitor) => onDragEnd(item, monitor),
    canDrag: (monitor) => draggable,
    collect: (monitor) => ({
      isDragging: !!monitor.isDragging(),
    }),
  });
  const [data, setData] = useState([]);

  useEffect(() => {
    if (table.getData) {
      table.getData().then((value) => {
        setData(value);
      });
    } else if (table.data) {
      setData(table.data);
    }
  }, [table]);

  const columns = useMemo(() => {
    if (data.length > 0) {
      const object = data[0];

      return Object.keys(object);
    }

    return [];
  }, [data]);

  return (
    <>
      {!draggable ? (
        data.length > 0 && table.getData ? (
          <BootstrapTable
            dark
            striped
            caption={table.name ? table.name : null}
            className={`${classes.scrollable} ${classes.scrollbar}`}
          >
            <Thead>
              <Tr>
                {columns.map((column, colKey) => (
                  <Th scope="col" key={colKey}>
                    {column}
                  </Th>
                ))}
              </Tr>
            </Thead>
            <Tbody>
              {data.map((row, rowKey) => (
                <Tr key={rowKey}>
                  {columns.map((column, colKey) => (
                    <Td
                      onClick={() => {
                        if (table.onTableClick) {
                          table.onTableClick(row, column);
                        }
                      }}
                      key={colKey}
                    >
                      {row[column]}
                    </Td>
                  ))}
                </Tr>
              ))}
            </Tbody>
          </BootstrapTable>
        ) : (
          <>
            <FrownOpenIcon size={50} color={'#272a30'} />
            <p className={classes.caption}>No data found.</p>
          </>
        )
      ) : (
        <div
          ref={drag}
          style={styles.draggable(isDragging)}
          className={classes.tableDraggable}
        >
          <TableIcon size={50} color={'#272a30'} />
          <p className={classes.caption}>{table.name}</p>
        </div>
      )}
    </>
  );
}

export default Table;
