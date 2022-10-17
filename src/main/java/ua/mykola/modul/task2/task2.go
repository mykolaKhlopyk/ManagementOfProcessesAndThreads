package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

//Variant 6
//Task №8

var (
	random           = rand.New(rand.NewSource(time.Now().UnixNano()))
	maxCashBoxMoney  = 1000
	partCashBoxMoney = 500
	maxStorageMoney  = 100000
	mutex            = &sync.Mutex{}
	clientAccountId  = -1
)

type Storage struct {
	money int
}

func newStorage() *Storage {
	return &Storage{
		money: random.Intn(maxStorageMoney) + 1000,
	}
}

type BankTeller struct {
	money int
}

func newBankTeller() BankTeller {
	return BankTeller{
		money: random.Intn(maxCashBoxMoney) + 1000,
	}
}

type ClientsAccount struct {
	id    int
	money int
}

func newClientAccount() ClientsAccount {
	clientAccountId++

	return ClientsAccount{
		id:    clientAccountId,
		money: random.Intn(100),
	}
}

type Bank struct {
	storage        *Storage
	bankTellers    []BankTeller
	clientAccounts []ClientsAccount
}

func (b *Bank) observe() {
	for i := range b.bankTellers {
		mutex.Lock()

		if b.bankTellers[i].money <= 0 {
			if b.storage.money >= maxStorageMoney {
				b.bankTellers[i].money += maxCashBoxMoney
			}
		}

		if b.bankTellers[i].money >= maxCashBoxMoney {
			b.storage.money += partCashBoxMoney
			b.bankTellers[i].money -= partCashBoxMoney
		}

		mutex.Unlock()
	}
}

func newBank() *Bank {
	bankTellers := make([]BankTeller, 10)
	clientAccounts := make([]ClientsAccount, 20)

	for i := range bankTellers {
		bankTellers[i] = newBankTeller()
	}

	for i := range clientAccounts {
		clientAccounts[i] = newClientAccount()
	}

	return &Bank{
		storage:        newStorage(),
		bankTellers:    bankTellers,
		clientAccounts: clientAccounts,
	}
}

func (b *Bank) getCashbox() *BankTeller {
	return &b.bankTellers[random.Intn(len(b.bankTellers))]
}

func (b *Bank) withdrawMoney(id, amount int) {
	mutex.Lock()
	if b.clientAccounts[id].money-amount >= 0 {
		b.clientAccounts[id].money -= amount
		b.getCashbox().money += amount
	}
	mutex.Unlock()
}

func (b *Bank) replenishMoney(id, amount int) {
	mutex.Lock()
	b.getCashbox().money -= amount
	b.clientAccounts[id].money += amount
	mutex.Unlock()
}

func (b *Bank) transferMoney(id, otherClientId, amount int) {
	mutex.Lock()
	if b.clientAccounts[id].money >= amount {
		b.clientAccounts[id].money -= amount
		b.clientAccounts[otherClientId].money += amount
	}
	mutex.Unlock()
}

func (b *Bank) pay(id, amount int) {
	mutex.Lock()
	if b.clientAccounts[id].money-amount >= 0 {
		b.clientAccounts[id].money -= amount
		b.getCashbox().money += amount
	}
	mutex.Unlock()
}

func (b *Bank) serveClients(clients []Client, endChan chan bool) {
	for i := range clients {
		clients[i].work(b)
	}
	rand.Shuffle(len(clients), func(i, j int) {
		clients[i], clients[j] = clients[j], clients[i]
	})
	endChan <- true
}

type Client struct {
	id int
}

func newClient(id int) Client {
	return Client{
		id: id,
	}
}

func (c *Client) addMoney(b *Bank) {
	amount := random.Intn(10)
	fmt.Printf("Client %v adds %v\n", c.id, amount)
	b.withdrawMoney(c.id, amount)
}

func (c *Client) takeMoney(b *Bank) {
	amount := random.Intn(10)
	fmt.Printf("Client %v takes %v\n", c.id, amount)
	b.replenishMoney(c.id, amount)
}

func (c *Client) transferMoney(b *Bank) {
	amount := random.Intn(10)
	otherClientId := random.Intn(clientAccountId)
	fmt.Printf("Client %v transfers to %v amount %v\n", c.id, otherClientId, amount)
	b.transferMoney(c.id, otherClientId, amount)
}

func (c *Client) pay(b *Bank) {
	amount := random.Intn(10)
	fmt.Printf("Client %v pays %v\n", c.id, amount)
	b.pay(c.id, amount)
}

func (c *Client) work(b *Bank) {
	decision := rand.Intn(4)
	switch decision {
	case 0:
		c.addMoney(b)
		break

	case 1:
		c.takeMoney(b)
		break

	case 2:
		c.transferMoney(b)
		break

	case 3:
		c.pay(b)
		break
	}
	time.Sleep(time.Millisecond * time.Duration(rand.Intn(700)))
}

func main() {
	bank := newBank()
	clients := make([]Client, clientAccountId+1)
	endChan := make(chan bool, 1)
	for i := range clients {
		clients[i] = newClient(i)
	}
	go bank.observe()
	go bank.serveClients(clients, endChan)
	<-endChan
}
