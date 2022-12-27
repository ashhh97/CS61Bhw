package game2048;

/** Describes a source of input commands.
 *  @author P. N. Hilfinger
 */
interface InputSource {

    /**
     * Returns one command string.
     */
    default String getKey() {
        return null;
    }

    /** Returns a candidate Tile whose row and column is in the range
     *  0 .. SIZE-1.  */
    Tile getNewTile(int size);

}

