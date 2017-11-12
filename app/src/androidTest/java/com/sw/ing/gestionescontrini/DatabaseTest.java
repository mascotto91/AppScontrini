package com.sw.ing.gestionescontrini;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import database.DAO;
import database.Ticket;
import database.TicketDatabase;
import database.TicketEntity;

/**
 * Created by Federico Taschin on 12/11/2017.
 */

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    TicketDatabase database;
    DAO ticketDAO;

    @Before
    public void setDatabase() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Context context = InstrumentationRegistry.getTargetContext();
        database = Room.inMemoryDatabaseBuilder(context, TicketDatabase.class).build();

        Method method = TicketDatabase.class.getDeclaredMethod("ticketDAO()");
        method.setAccessible(true);
        ticketDAO = (DAO) method.invoke(database, null);
    }

    public void insertTicketTest(){
        TicketEntity ticket = new TicketEntity();
        ticket.setShop("Decathlon");
        ticketDAO.addTicket(ticket);
    }
}
