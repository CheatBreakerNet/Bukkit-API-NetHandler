package com.cheatbreaker.nethandler.server;


import com.cheatbreaker.nethandler.ByteBufWrapper;
import com.cheatbreaker.nethandler.CBPacket;
import com.cheatbreaker.nethandler.ICBNetHandler;
import com.cheatbreaker.nethandler.client.ICBNetHandlerClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CBPacketSwingInhibitorUpdate extends CBPacket {

    private int type = 0;

    private int frequencyMs;
    private double decayRate;
    private int cpsCap;

    public CBPacketSwingInhibitorUpdate(int frequencyMs, double decayRate, int cpsCap) {
        this.type = 0;
        this.frequencyMs = frequencyMs;
        this.decayRate = decayRate;
        this.cpsCap = cpsCap;
    }

    @Override
    public void write(ByteBufWrapper out) throws IOException {
        out.buf().writeInt(this.type);
        out.buf().writeInt(this.frequencyMs);
        out.buf().writeDouble(this.decayRate);
        out.buf().writeInt(this.cpsCap);
    }

    @Override
    public void read(ByteBufWrapper in) throws IOException {
        this.type = in.buf().readInt();
        this.frequencyMs = in.buf().readInt();
        this.decayRate = in.buf().readDouble();
        this.cpsCap = in.buf().readInt();
    }

    @Override
    public void process(ICBNetHandler iCBNetHandler) {
        ((ICBNetHandlerClient) iCBNetHandler).handleSwingInhibitorUpdate(this);
    }
}

