package com.example.homeschoolplatform.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName chat
 */
@Data
public class Chat implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer initiatorId;

    /**
     * 
     */
    private Integer recipientId;

    /**
     * 
     */
    private Integer initiatorOnline;

    /**
     * 
     */
    private Integer recipientOnline;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Chat other = (Chat) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getInitiatorId() == null ? other.getInitiatorId() == null : this.getInitiatorId().equals(other.getInitiatorId()))
            && (this.getRecipientId() == null ? other.getRecipientId() == null : this.getRecipientId().equals(other.getRecipientId()))
            && (this.getInitiatorOnline() == null ? other.getInitiatorOnline() == null : this.getInitiatorOnline().equals(other.getInitiatorOnline()))
            && (this.getRecipientOnline() == null ? other.getRecipientOnline() == null : this.getRecipientOnline().equals(other.getRecipientOnline()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getInitiatorId() == null) ? 0 : getInitiatorId().hashCode());
        result = prime * result + ((getRecipientId() == null) ? 0 : getRecipientId().hashCode());
        result = prime * result + ((getInitiatorOnline() == null) ? 0 : getInitiatorOnline().hashCode());
        result = prime * result + ((getRecipientOnline() == null) ? 0 : getRecipientOnline().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", initiatorId=").append(initiatorId);
        sb.append(", recipientId=").append(recipientId);
        sb.append(", initiatorOnline=").append(initiatorOnline);
        sb.append(", recipientOnline=").append(recipientOnline);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}