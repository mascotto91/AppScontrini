package database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ColumnInfo;
import android.net.Uri;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Represents the ticket and its associated information
 * @author Marco Olivieri (Team 3)
 */

@Entity(tableName = Constants.TICKET_TABLE_NAME,
        foreignKeys = @ForeignKey(entity = MissionEntity.class, parentColumns = Constants.MISSION_PRIMARY_KEY_NAME, childColumns = Constants.MISSION_CHILD_COLUMNS))
@TypeConverters(Converters.class)

public class TicketEntity implements Serializable{

    @ColumnInfo(name = Constants.TICKET_PRIMARY_KEY_NAME)
    @PrimaryKey(autoGenerate = true)
    private int ID;
    private Uri fileUri;
    private BigDecimal amount;
    private String shop;
    private Date date;
    private String title;
    private String category;

    @ColumnInfo(name = Constants.MISSION_CHILD_COLUMNS)
    private int missionID;

    @Ignore
    /**
     * Non parametric constructor
     */
    public TicketEntity() {
    }

    /**
     * Parametric constructor
     *
     * @param fileUri Path associated with the the ticket file stored in the memory
     * @param amount total amount
     * @param shop Name of the shop in which the ticket was issued
     * @param date the issue date of the ticket
     * @param title name given
     * @param missionID code of the mission
     */
    public TicketEntity(Uri fileUri, BigDecimal amount, String shop, Date date, String title, int missionID) {
        this.amount = amount;
        this.date = date;
        this.fileUri = fileUri;
        this.shop = shop;
        this.title = title;
        this.missionID = missionID;
    }

    /**
     * Returns the ticket ID
     * @return  ID
     */
    public int getID() { return ID; }

    @Deprecated
    /** This method should no longer exists, since the ID is auto-generated by the database
     * Set ticket id
     * @param ID not null
     */
    public void setID(int ID) { this.ID = ID; }

    /**
     * Returns the date of the issue of the ticket
     *
     * @return date
     */
    public  Date getDate() { return date; }

    /**
     * Sets ticket date
     * @param date not null
     */
    public void setDate(Date date) { this.date = date; }

    /**
     * Returns ticket amount
     * @return amount
     */
    public  BigDecimal getAmount() { return amount; }

    /**
     * Sets ticket amount
     * @param amount not null
     */
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    /**
     * Returns ticket file path
     * @return fileUri
     */
    public Uri getFileUri() { return fileUri; }

    /**
     * Sets ticket file path
     * @param fileUri not null
     */
    public void setFileUri(Uri fileUri) { this.fileUri = fileUri; }

    /**
     * Returns the shop
     * @return shop
     */
    public String getShop() { return shop; }

    /**
     *
     * @param shop not null
     */
    public void setShop (String shop) { this.shop = shop; }

    /**
     * Restituisce il titolo del TicketEntity
     *
     * @return title
     */
    public String getTitle() { return title; }

    /**
     * Imposta il titolo del TicketEntity
     *
     * @param title not null
     */
    public void setTitle (String title) { this.title = title; }

    /**
     * Restituisce la categoria del TicketEntity
     *
     * @return title
     */
    public String getCategory() { return category; }

    /**
     * Imposta la categoria del TicketEntity
     *
     * @param category not null
     */
    public void setCategory (String category) { this.category = category; }

    /**
     * Returns the mission id of this ticket
     * @return missionID
     */
    public int getMissionID() {
        return missionID;
    }

    /**
     * Sets mission id of this TicketEntity
     * @param missionID
     */
    public void setMissionID(int missionID) {
        this.missionID = missionID;
    }


    /**
     * Returns a String with TicketEntity data formatted as follows:
     * "Shop"
     * Totale: "Amount"
     *
     * @return string
     */
    @Override
    public String toString(){
        return getShop()+"\nTotale: "+getAmount();
    }
}