package com.tpf_benchmarks.http_server.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Votes")
@IdClass(VoteId.class)
public class Vote {

        @Id
        @Column(name = "user_id")
        private Integer userId;

        @Id
        @Column(name = "poll_id")
        private Integer pollId;

        @Column(name = "option_num")
        private Integer optionNum;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        @MapsId("userId")
        private User user;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "poll_id")
        @MapsId("pollId")
        private Poll poll;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumns({
                @JoinColumn(name = "poll_id", referencedColumnName = "poll_id", insertable = false, updatable = false),
                @JoinColumn(name = "option_num", referencedColumnName = "option_num", insertable = false, updatable = false)
        })
        @NotNull
        private PollOption pollOption;
}
