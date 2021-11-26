package boardgame;

// classe responsável pelo tabuleiro
public class Board {

    private Integer rows;
    private Integer columns;
    private Piece[][] pieces;

    public Board(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    // retorna a peça dada uma linha e uma coluna
    public Piece piece(Integer row, Integer column) {
        return pieces[row][column];
    }

    // retorna a peça pela posição
    public Piece piece(Position position){
        return pieces[position.getRow()][position.getColumn()];
    }
}
