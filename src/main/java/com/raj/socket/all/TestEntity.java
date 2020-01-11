package com.raj.socket.all;

import javax.persistence.*;

@Entity
@Table(name = "TEST",schema = "RITURAJ")
public class TestEntity {
    @Id
    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testId;
    @Column(name = "NAME")
    private String name;

    public Integer getTestId() {
        return testId;
    }

    public TestEntity setTestId(Integer testId) {
        this.testId = testId;
        return this;
    }

    public String getName() {
        return name;
    }

    public TestEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "testId=" + testId +
                ", name='" + name + '\'' +
                '}';
    }
}
