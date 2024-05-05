package com.tpf_benchmarks.http_server.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PollOptions")
@IdClass(PollOptionId.class)
public class PollOption {

        @Id
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "poll_id")
        private Poll poll;

        @Id
        @Column(name = "option_num")
        private Integer optionNum;

        @Column(name = "option_text")
        private String optionText;

}
