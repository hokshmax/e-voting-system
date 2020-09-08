package com.example.votingsystem;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class Contracts_Election_sol_Election extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060408051808201909152600b81526a43616e646964617465203160a81b6020820152610045906001600160e01b0361007e16565b60408051808201909152600b81526a21b0b73234b230ba32901960a91b6020820152610079906001600160e01b0361007e16565b61017a565b600280546001908101918290556040805160608101825283815260208082018681526000838501819052958652848252929094208151815591518051919492936100ce93908501929101906100df565b506040820151816002015590505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061012057805160ff191683800117855561014d565b8280016001018555821561014d579182015b8281111561014d578251825591602001919060010190610132565b5061015992915061015d565b5090565b61017791905b808211156101595760008155600101610163565b90565b61035a806101896000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c80630121b93f146100515780632d35a8a2146100705780633477ee2e1461008a578063a3ec138d1461012d575b600080fd5b61006e6004803603602081101561006757600080fd5b5035610167565b005b61007861025d565b60408051918252519081900360200190f35b6100a7600480360360208110156100a057600080fd5b5035610263565b6040518084815260200180602001838152602001828103825284818151815260200191508051906020019080838360005b838110156100f05781810151838201526020016100d8565b50505050905090810190601f16801561011d5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b6101536004803603602081101561014357600080fd5b50356001600160a01b031661030f565b604080519115158252519081900360200190f35b3360009081526020819052604090205460ff16156101b5576040805162461bcd60e51b815260206004808301919091526024820152631cd91cd960e21b604482015290519081900360640190fd5b6000811180156101c757506002548111155b610201576040805162461bcd60e51b815260206004808301919091526024820152637373737360e01b604482015290519081900360640190fd5b33600090815260208181526040808320805460ff191660019081179091558484529182905280832060020180549092019091555182917ffff3c900d938d21d0990d786e819f29b8d05c1ef587b462b939609625b684b1691a250565b60025481565b600160208181526000928352604092839020805481840180548651600296821615610100026000190190911695909504601f810185900485028601850190965285855290949193929091908301828280156102ff5780601f106102d4576101008083540402835291602001916102ff565b820191906000526020600020905b8154815290600101906020018083116102e257829003601f168201915b5050505050908060020154905083565b60006020819052908152604090205460ff168156fea2646970667358221220992e3624e0a3f06a7b9187233fa3099cc04e0a8c758eebc47415b92e025fd28364736f6c63430006060033";

    public static final String FUNC_CANDIDATES = "candidates";

    public static final String FUNC_CANDIDATESCOUNT = "candidatesCount";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_VOTERS = "voters";

    public static final Event VOTEDEVENT_EVENT = new Event("votedEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected Contracts_Election_sol_Election(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Contracts_Election_sol_Election(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Contracts_Election_sol_Election(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Contracts_Election_sol_Election(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<VotedEventEventResponse> getVotedEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOTEDEVENT_EVENT, transactionReceipt);
        ArrayList<VotedEventEventResponse> responses = new ArrayList<VotedEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VotedEventEventResponse typedResponse = new VotedEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._candidateId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<VotedEventEventResponse> votedEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, VotedEventEventResponse>() {
            @Override
            public VotedEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VOTEDEVENT_EVENT, log);
                VotedEventEventResponse typedResponse = new VotedEventEventResponse();
                typedResponse.log = log;
                typedResponse._candidateId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<VotedEventEventResponse> votedEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTEDEVENT_EVENT));
        return votedEventEventFlowable(filter);
    }

    public RemoteFunctionCall<Tuple3<BigInteger, String, BigInteger>> candidates(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CANDIDATES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, String, BigInteger>>(function,
                new Callable<Tuple3<BigInteger, String, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> candidatesCount() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CANDIDATESCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> vote(BigInteger _candidateId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_candidateId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> voters(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VOTERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static Contracts_Election_sol_Election load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_Election_sol_Election(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Contracts_Election_sol_Election load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_Election_sol_Election(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Contracts_Election_sol_Election load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Contracts_Election_sol_Election(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Contracts_Election_sol_Election load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Contracts_Election_sol_Election(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Contracts_Election_sol_Election> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Contracts_Election_sol_Election.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Contracts_Election_sol_Election> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Contracts_Election_sol_Election.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Contracts_Election_sol_Election> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Contracts_Election_sol_Election.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Contracts_Election_sol_Election> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Contracts_Election_sol_Election.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class VotedEventEventResponse extends BaseEventResponse {
        public BigInteger _candidateId;
    }
}
