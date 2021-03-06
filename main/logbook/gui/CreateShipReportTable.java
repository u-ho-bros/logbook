package logbook.gui;

import logbook.data.context.GlobalContext;
import logbook.gui.logic.CreateReportLogic;
import logbook.gui.logic.TableItemCreator;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;

/**
 * 建造報告書
 *
 */
public final class CreateShipReportTable extends AbstractTableDialog {

    /**
     * @param parent
     */
    public CreateShipReportTable(Shell parent) {
        super(parent);
    }

    @Override
    protected void createContents() {
    }

    @Override
    protected String getTitle() {
        return "建造報告書";
    }

    @Override
    protected Point getSize() {
        return new Point(600, 350);
    }

    @Override
    protected String[] getTableHeader() {
        return CreateReportLogic.getCreateShipHeader();
    }

    @Override
    protected void updateTableBody() {
        this.body = CreateReportLogic.getCreateShipBody(GlobalContext.getGetshipList());
    }

    @Override
    protected TableItemCreator getTableItemCreator() {
        return TableItemCreator.DEFAULT_TABLE_ITEM_CREATOR;
    }

    @Override
    protected SelectionListener getHeaderSelectionListener() {
        return new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (e.getSource() instanceof TableColumn) {
                    CreateShipReportTable.this.sortTableItems((TableColumn) e.getSource());
                }
            }
        };
    }
}
