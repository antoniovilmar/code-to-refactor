package com.example.demo.application;


class ContaServiceTest {
//
//    private ContaService contaService;
//    private ContaRepository contaRepository;
//    private CriacaoContaStrategy accountCreationOne;
//    private CriacaoContaStrategy accountCreationTwo;
//    private CriacaoContaStrategy accountCreationThree;
//    private GeradorNumeroContaFachada geradorNumeroContaFachada;
//    private GeradorNumeroContaInvestimentoFachada geradorNumeroContaInvestimentoFachada;
//
//    @BeforeEach
//    public void init() {
//        contaRepository = mock(ContaRepository.class);
//
//        accountCreationOne = mock(CriacaoContaStrategy.class);
//        accountCreationTwo = mock(CriacaoContaStrategy.class);
//
//        List<CriacaoContaStrategy> accountsCreation = new ArrayList<>();
//        accountsCreation.add(accountCreationOne);
//        accountsCreation.add(accountCreationTwo);
//
//        geradorNumeroContaFachada = mock(GeradorNumeroContaFachada.class);
//        geradorNumeroContaInvestimentoFachada = mock(GeradorNumeroContaInvestimentoFachada.class);
//
//
//        contaService = new ContaServiceImpl(contaRepository,
//                accountsCreation,
//                geradorNumeroContaFachada,
//                geradorNumeroContaInvestimentoFachada);
//    }
//
//
//    @Test
//    @DisplayName("Deve abrir conta corrente")
//    void deveAbrirContaCorrente() {
//        long numeroConta = 28L;
//        long agencia = 123L;
//        String cpfTitular = "028";
//
//        var contaEsperada = new ContaCorrente(numeroConta,agencia, cpfTitular);
//        AberturaContaDto dadosAberturaConta = new AberturaContaDto(agencia,cpfTitular);
//        dadosAberturaConta.setAccountType(TipoConta.CORRENTE);
//        var contaCriada = new ContaCorrente(numeroConta,agencia, cpfTitular);
//
//        when(geradorNumeroContaFachada.gerar()).thenReturn(numeroConta);
//        when(accountCreationOne.ifAccountType(TipoConta.CORRENTE)).thenReturn(true);
//        when(accountCreationOne.create(numeroConta, agencia, cpfTitular)).thenReturn(contaEsperada);
//        when(contaRepository.save(contaEsperada)).thenReturn(contaCriada);
//
//
//        long numeroContaCriada = contaService.openAccount(dadosAberturaConta);
//        Assertions.assertEquals(numeroConta, numeroContaCriada);
//
//        //balance nao ta sendo setado é 0?
//
//    }
}