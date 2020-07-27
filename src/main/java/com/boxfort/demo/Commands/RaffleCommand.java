package com.boxfort.demo.Commands;


import com.boxfort.demo.Abstracts.AbstractCommand;
import com.boxfort.demo.Abstracts.CustomEvent;
import com.boxfort.demo.Entities.Raffle;
import com.boxfort.demo.dao.RaffleDAO;
import com.boxfort.demo.dao.RaffleMemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RaffleCommand extends AbstractCommand {
    private final RaffleDAO raffleDAO;
    private final RaffleMemberDAO raffleMemberDAO;

    @Autowired
    public RaffleCommand(RaffleDAO raffleDAO, RaffleMemberDAO raffleMemberDAO)
    {
        super("raffle");
        this.raffleDAO = raffleDAO;
        this.raffleMemberDAO = raffleMemberDAO;
    }


    @Override
    public void execute(CustomEvent event) {
        raffleDAO.findByRaffleIdGreaterThanX(2L).forEach(raffle ->
                System.out.println(raffle.getId())
        );
    }
}
